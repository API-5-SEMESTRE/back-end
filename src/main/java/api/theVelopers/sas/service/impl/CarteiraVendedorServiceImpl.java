package api.theVelopers.sas.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.theVelopers.sas.entity.CarteiraVendedor;
import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.entity.Usuario;
import api.theVelopers.sas.enumeration.TipoEmpresa;
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
		
		empresa.setUsuario(usuario);
		if(empresa.getOrigem() != TipoEmpresa.SPC) {
			empresa.setOrigem(TipoEmpresa.SPC);
		}
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-mm-yyyy HH:mm:ss");
		LocalDateTime dataCadastroVendedor = LocalDateTime.parse(LocalDateTime.now().toString(), formatador);
		
		empresa.setDataDeCadastroVendedor(dataCadastroVendedor);
	}
	
	@Override
	public void removerVendedorEmpresa(Long cnpjEmpresa) {
		Empresa empresa = empresaRepo.findById(cnpjEmpresa).get();
		
		empresa.setUsuario(null);
		
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
		carteiraVendedor.setVendedor(vendedor);
		carteiraVendedor.setScore(soma);
		
		return carteiraVendedor;
	}
	
}
