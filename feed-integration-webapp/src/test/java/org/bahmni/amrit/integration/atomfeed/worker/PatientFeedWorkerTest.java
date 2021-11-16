package org.bahmni.amrit.integration.atomfeed.worker;

import org.bahmni.amrit.integration.atomfeed.OpenMRSMapperBaseTest;
import org.bahmni.amrit.integration.services.OpenMRSService;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

public class PatientFeedWorkerTest extends OpenMRSMapperBaseTest {

    @Mock
    private OpenMRSService openMRSService;

    @InjectMocks
    private PatientFeedWorker patientFeedWorker = new PatientFeedWorker();

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    /*@Test
    public void shouldGetEncounterDataFromTheEventContentAndSaveIt() throws Exception {
        String content = "/openmrs/encounter/uuid1";
        OpenMRSOrder order = new OpenMRSOrder();
        OpenMRSEncounter openMRSEncounter = new OpenMRSEncounter();
        openMRSEncounter.addTestOrder(order);
        when(openMRSService.getEncounter(content)).thenReturn(openMRSEncounter);

        encounterFeedWorker.process(new Event("event id", content));

        verify(pacsIntegrationService, times(1)).processEncounter(openMRSEncounter);
    }

    @Test
    public void shouldNotProcessEncounterIfNoOrdersInIt() throws Exception {
        String content = "/openmrs/encounter/uuid1";
        OpenMRSEncounter openMRSEncounter = new OpenMRSEncounter();
        when(openMRSService.getEncounter(content)).thenReturn(openMRSEncounter);

        encounterFeedWorker.process(new Event("event id", content));

        verify(pacsIntegrationService, times(0)).processEncounter(openMRSEncounter);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionIfJsonParseFails() throws Exception {
        String content = "something";
        when(openMRSService.getEncounter(content)).thenThrow(new IOException("Incorrect JSON"));

        encounterFeedWorker.process(new Event("event id", content));
    }

    @Test
    public void shouldFilterOutBedAssignmentEventsBeforeProcessing() throws Exception {
        String content = "/openmrs/encounter/uuid1";
        OpenMRSOrder order = new OpenMRSOrder();
        OpenMRSEncounter openMRSEncounter = new OpenMRSEncounter();
        openMRSEncounter.addTestOrder(order);

        encounterFeedWorker.process(new Event("event id", content, "Bed-Assignment"));

        verify(pacsIntegrationService, times(0)).processEncounter(openMRSEncounter);
    }*/
}