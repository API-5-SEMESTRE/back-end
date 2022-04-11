package api.theVelopers.sas.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.theVelopers.sas.entity.CarteiraVendedor;
import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.entity.Usuario;
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
		Usuario usuario = usuarioRepo.getById(idUsuario);
		Empresa empresa = empresaRepo.getById(cnpjEmpresa);
		
		empresa.setUsuario(usuario);
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-mm-yyyy HH:mm:ss");
		LocalDateTime dataCadastroVendedor = LocalDateTime.parse(LocalDateTime.now().toString(), formatador);
		
		empresa.setDataDeCadastroVendedor(dataCadastroVendedor);
	}
	
	@Override
	public void removerVendedorEmpresa(Long cnpjEmpresa) {
		Empresa empresa = empresaRepo.getById(cnpjEmpresa);
		
		empresa.setUsuario(null);
		
		empresaRepo.save(empresa);
	}
	
	@Override
	public CarteiraVendedor criarCarteiraVendedor(Long idVendedor) {
		Usuario vendedor = usuarioRepo.getById(idVendedor);
		List<Empresa> empresasQueVendedorAtua = empresaRepo.findByUsuario(vendedor);
		Long soma = consumoRepo.procurarPorSomaConsumoPorVendedor(idVendedor);
		
		CarteiraVendedor carteiraVendedor = new CarteiraVendedor();
		carteiraVendedor.setClientes(empresasQueVendedorAtua);
		carteiraVendedor.setVendedor(vendedor);
		carteiraVendedor.setScore(soma);
		
		return carteiraVendedor;
	}
	
}
