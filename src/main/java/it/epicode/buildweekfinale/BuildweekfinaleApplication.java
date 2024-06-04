package it.epicode.buildweekfinale;

import it.epicode.buildweekfinale.entity.Cliente;
import it.epicode.buildweekfinale.entity.Utente;
import it.epicode.buildweekfinale.enums.RuoloUtente;
import it.epicode.buildweekfinale.repository.ClienteRepository;
import it.epicode.buildweekfinale.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;

@SuppressWarnings("all")
@SpringBootApplication
public class BuildweekfinaleApplication implements CommandLineRunner {

	@Autowired
	private UtenteRepository utenteRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BuildweekfinaleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Utente admin = new Utente();
		admin.setUsername("admin");
		admin.setEmail("riccardodelpiccolo1@gmail.com");
		admin.setPassword("admin");
		admin.setNome("Riccardo");
		admin.setCognome("DelPiccolo");
		admin.setAvatar("admin_avatar.png");
		admin.setRuoloUtente(RuoloUtente.ADMIN);
		utenteRepository.save(admin);

		Utente utente1 = new Utente();
		utente1.setUsername("utente");
		utente1.setEmail("utente1@gmail.com");
		utente1.setPassword("password");
		utente1.setNome("Franchino");
		utente1.setCognome("ErCriminale");
		utente1.setAvatar("utente1_avatar.png");
		utente1.setRuoloUtente(RuoloUtente.USER);
		utenteRepository.save(utente1);

		Cliente cliente1 = new Cliente();
		cliente1.setRagioneSociale("Epicode");
		cliente1.setPartitaIVA("123456789");
		cliente1.setEmail("epicode@epicode.com");
		cliente1.setDataInserimento(LocalDate.now());
		cliente1.setDataUltimoContatto(LocalDate.now());
		cliente1.setFatturatoAnnuale(BigDecimal.valueOf(1000000));
		cliente1.setPec("epicode@pec.com");
		cliente1.setTelefono("+39 333 333 3333");
		cliente1.setEmailContatto("epicode@gmail.com");
		cliente1.setNomeContatto("Epicode");
		cliente1.setCognomeContatto("Epicoder");
		cliente1.setTelefonoContatto("+39 333 666 9999");
		cliente1.setLogoAziendale("epicode_logo.png");
		clienteRepository.save(cliente1);

		Cliente cliente2 = new Cliente();
		cliente2.setRagioneSociale("NextDevs");
		cliente2.setPartitaIVA("987654321");
		cliente2.setEmail("nextdevs@nextdevs.com");
		cliente2.setDataInserimento(LocalDate.now());
		cliente2.setDataUltimoContatto(LocalDate.now());
		cliente2.setFatturatoAnnuale(BigDecimal.valueOf(500000));
		cliente2.setPec("nextdevs@pec.com");
		cliente2.setTelefono("+39 333 999 6666");
		cliente2.setEmailContatto("nextdevs@gmail.com");
		cliente2.setNomeContatto("Next");
		cliente2.setCognomeContatto("Devs");
		cliente2.setTelefonoContatto("+39 333 696 9669");
		cliente2.setLogoAziendale("nextdevs_logo.png");
		clienteRepository.save(cliente2);
	}

}
