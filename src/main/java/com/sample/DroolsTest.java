package com.sample;

import java.text.SimpleDateFormat;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sample.Client.Type;
import com.sample.Car.*;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

	public static final void main(String[] args) {
		try {
			// Utwórz bazê wiedzy
			KnowledgeBase kbase = readKnowledgeBase();
			// Stwórz silnik wnioskowania, zawieraj¹cy pamiêæ robocz¹ i
			// uruchamiaj¹cy regu³y
			StatefulKnowledgeSession ksession = kbase
					.newStatefulKnowledgeSession();
			// Utwórz logger, który umo¿liwia przeœledzenie procesu wnioskowania
			KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory
					.newFileLogger(ksession, "test");
			// Dodaj do pamiêci roboczej obiekt typu Message
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			Client kl = new Client();
			kl.type = Type.LOYAL;
			Reservation r1 = new Reservation(kl, sdf.parse("11-11-2016"),
					sdf.parse("12-11-2016"));
			r1.numEq = 1;
			r1.client = kl;

			ReservationReturn ret = new ReservationReturn(kl, 111,
					sdf.parse("14-11-2016"));
			ret.docsMissing++;
			ksession.insert(ret);
			ksession.insert(kl);
			ksession.insert(r1);

			Car aCar1 = new ACar();
			Car aCar2 = new ACar();
			Car bCar1 = new BCar();
			Car dCar1 = new DCar();
			Car dCar2 = new DCar();

			// ksession.insert(aCar1);
			// ksession.insert(aCar2);
			ksession.insert(bCar1);
			ksession.insert(dCar1);
			ksession.insert(dCar2);

			// Uruchom wszystkie regu³y dostêpne w agendzie
			ksession.fireAllRules();

			// Zakoñcz rejestracjê zdarzeñ.
			logger.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private static KnowledgeBase readKnowledgeBase() throws Exception {
		// Utwórz obiekt KnowledgeBuilder, który potrafi wczytywaæ regu³y z
		// ró¿nych Ÿróde³
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		// Dodaj regu³y zawarte w Sample.drl w zasobach programu, zapisane w
		// formacie DRL
		kbuilder.add(ResourceFactory.newClassPathResource("rules/Sample.drl"),
				ResourceType.DRL);
		// Wyszukaj b³êdy
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		// Je¿eli s¹ b³êdy wyœwietl je i rzuæ wyj¹tek
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		// Stwórz pust¹ bazê wiedzy
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		// Dodaj do niej wczytane regu³y
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}

}
