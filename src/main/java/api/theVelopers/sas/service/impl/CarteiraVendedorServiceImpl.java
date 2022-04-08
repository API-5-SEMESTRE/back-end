package api.theVelopers.sas.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.theVelopers.sas.entity.CarteiraVendedor;
import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.entity.Usuario;
import api.theVelopers.sas.service.CrudService;
import api.theVelopers.sas.service.CarteiraVendedorService;
import api.theVelopers.sas.service.EmpresaService;

@Service
public class CarteiraVendedorServiceImpl implements CarteiraVendedorService{
	
	@Autowired
	EmpresaService empresaService;
	@Autowired
	CrudService<Usuario, Long> crudUsuario;
	
	@Override
	public void adicionarVendedorEmpresa(Long idUsuario, Long cnpjEmpresa) {
		Usuario usuario = crudUsuario.procurarPorId(idUsuario).get();
		Empresa empresa = empresaService.procurarPorId(cnpjEmpresa).get();
		
		empresa.setUsuario(usuario);
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-mm-yyyy HH:mm:ss");
		LocalDateTime dataCadastroVendedor = LocalDateTime.parse(LocalDateTime.now().toString(), formatador);
		
		empresa.setDataDeCadastroVendedor(dataCadastroVendedor);
	}
	
	@Override
	public void removerVendedorEmpresa(Long cnpjEmpresa) {
		Empresa empresa = empresaService.procurarPorId(cnpjEmpresa).get();
		
		empresa.setUsuario(null);
		
		empresaService.salvarFlush(empresa);
	}
	
	@Override
	public CarteiraVendedor criarCarteiraVendedor(Long idVendedor) {
		Usuario vendedor = crudUsuario.procurarPorId(idVendedor).get();
		List<Empresa> empresasQueVendedorAtua = empresaService.findByUsuario(vendedor);
		
		CarteiraVendedor carteiraVendedor = new CarteiraVendedor();
		carteiraVendedor.setClientes(empresasQueVendedorAtua);
		carteiraVendedor.setVendedor(vendedor);
		
		return carteiraVendedor;
	}
	
}
