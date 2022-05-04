package api.theVelopers.sas.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.theVelopers.sas.entity.CarteiraVendedor;
import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.entity.Usuario;
import api.theVelopers.sas.enumeration.TipoEmpresa;
import api.theVelopers.sas.enumeration.TipoUsuario;
import api.theVelopers.sas.repository.ConsumoRepository;
import api.theVelopers.sas.repository.EmpresaRepository;
import api.theVelopers.sas.repository.UsuarioRepository;
import api.theVelopers.sas.service.CarteiraVendedorService;

@Service
public class CarteiraVendedorServiceImpl implements CarteiraVendedorService{
	
	@Autowired
	private EmpresaRepository empresaRepo;
	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private ConsumoRepository consumoRepo;
	
	@Override
	public void adicionarVendedorEmpresa(Long idUsuario, Long cnpjEmpresa) {
		Usuario usuario = usuarioRepo.findById(idUsuario).get();
		Empresa empresa = empresaRepo.findById(cnpjEmpresa).get();
		
		if(empresa.getDataDeCadastroVendedor() != null) {
			throw new RuntimeException("Empresa já possui vendedor associado");
		}
		
		if(usuario.getTipoAcesso() != TipoUsuario.VENDEDOR) {
			throw new RuntimeException("Tipo de usuário não possui carteira");
		}
		
		empresa.setUsuario(usuario);
		if(empresa.getOrigem() != TipoEmpresa.SPC) {
			empresa.setOrigem(TipoEmpresa.SPC);
		}
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime hoje = LocalDateTime.now();
		String hojeFormatado = hoje.format(formatador);
		
		LocalDateTime dataCadastroVendedor = LocalDateTime.parse(hojeFormatado, formatador);
		
		empresa.setDataDeCadastroVendedor(dataCadastroVendedor);
		
		empresaRepo.saveAndFlush(empresa);
	}
	
	@Override
	public void removerVendedorEmpresa(Long cnpjEmpresa) {
		Empresa empresa = empresaRepo.findById(cnpjEmpresa).get();
		
		empresa.setUsuario(null);
		empresa.setDataDeCadastroVendedor(null);
		
		empresa.setOrigem(TipoEmpresa.LIVRE);
		
		empresaRepo.save(empresa);
	}
	
	@Override
	public CarteiraVendedor criarCarteiraVendedor(Long idVendedor) {
		Usuario vendedor = usuarioRepo.findById(idVendedor).get();
		List<Empresa> empresasQueVendedorAtua = empresaRepo.findByUsuario(vendedor);
		Long soma = consumoRepo.procurarPorSomaConsumoPorVendedor(idVendedor);
		vendedor.getEmail();
		CarteiraVendedor carteiraVendedor = new CarteiraVendedor();
		carteiraVendedor.setClientes(empresasQueVendedorAtua);
		carteiraVendedor.setVendedor(Usuario.paraDTO(vendedor));
		carteiraVendedor.setScore(soma == null? 0l:soma);
		
		return carteiraVendedor;
	}
	
	@Override
	public List<CarteiraVendedor> procurarMelhoresVendedores() {
		List<Usuario> vendedores = usuarioRepo.findAllVendedor();
		List<CarteiraVendedor> carteiras = new ArrayList<>();
		
		vendedores.forEach(v -> {
			CarteiraVendedor carteira = criarCarteiraVendedor(v.getId());
			carteiras.add(carteira);
		});
		
		Comparator<CarteiraVendedor> compararScore = Comparator.comparing(
				CarteiraVendedor::getScore);
		
		List<CarteiraVendedor> resultado = carteiras.stream().sorted(compararScore.reversed()).collect(Collectors.toList());
		
		return resultado.subList(0, 3);
	}
	
}
