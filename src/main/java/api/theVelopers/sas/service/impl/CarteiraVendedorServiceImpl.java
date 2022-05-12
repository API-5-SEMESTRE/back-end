package api.theVelopers.sas.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.theVelopers.sas.entity.CarteiraVendedor;
import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.entity.Usuario;
import api.theVelopers.sas.enumeration.SenioridadeVendedor;
import api.theVelopers.sas.enumeration.TipoEmpresa;
import api.theVelopers.sas.enumeration.TipoUsuario;
import api.theVelopers.sas.exception.NegocioException;
import api.theVelopers.sas.repository.ConsumoRepository;
import api.theVelopers.sas.repository.EmpresaRepository;
import api.theVelopers.sas.repository.UsuarioRepository;
import api.theVelopers.sas.service.CarteiraVendedorService;
import api.theVelopers.sas.utils.LocalDateTimeFormatterUtils;

import static api.theVelopers.sas.constant.MensagemErroConstant.*;
/**
 * 
 * @author jef
 *
 */
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
			throw new NegocioException(EMPRESA_JA_POSSUI_VENDEDOR);
		}
		
		if(usuario.getTipoAcesso() != TipoUsuario.VENDEDOR) {
			throw new NegocioException(USUARIO_NAO_POSSUI_CARTEIRA);
		}
		
		empresa.setUsuario(usuario);
		if(empresa.getOrigem() != TipoEmpresa.SPC) {
			empresa.setOrigem(TipoEmpresa.SPC);
		}
		
		LocalDateTime hoje = LocalDateTime.now();
		
		LocalDateTime dataCadastroVendedor = LocalDateTimeFormatterUtils.padronizarLocalDateTime(hoje);
		
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
		
		SenioridadeVendedor senioridade = vendedor.getDataCriacao() == null ?
											SenioridadeVendedor.JUNIOR :
											calcularSenioridade(vendedor.getDataCriacao());
		carteiraVendedor.setSenioridade(senioridade);
		
		return carteiraVendedor;
	}
	
	private SenioridadeVendedor calcularSenioridade(LocalDateTime dataInicio) {
		LocalDateTime agora = LocalDateTime.now();
		int anoAgora = agora.getYear();
		int anoInicio = dataInicio.getYear();
		
		int resultado = anoAgora - anoInicio;
		
		if(resultado <= 2) {
			return SenioridadeVendedor.JUNIOR;
		} else if(resultado <= 5) {
			return SenioridadeVendedor.PLENO;
		} 
		
		return SenioridadeVendedor.SENIOR;
	}
	
	@Override
	public Map<String, Long> procurarMelhoresVendedores() {
		List<Usuario> vendedores = usuarioRepo.findAllVendedor();
		List<CarteiraVendedor> carteiras = new ArrayList<>();
		Map<String, Long> ranking = new LinkedHashMap<>();
		
		vendedores.forEach(v -> {
			CarteiraVendedor carteira = criarCarteiraVendedor(v.getId());
			carteiras.add(carteira);
		});
		
		Comparator<CarteiraVendedor> compararScore = Comparator.comparing(
				CarteiraVendedor::getScore);
		
		List<CarteiraVendedor> resultado = carteiras.stream().sorted(compararScore.reversed()).collect(Collectors.toList());
		resultado = resultado.subList(0, 3);
		
		resultado.forEach(r->ranking.put(r.getVendedor().getNome(), r.getScore()));
		
		return ranking;
	}
	
}
