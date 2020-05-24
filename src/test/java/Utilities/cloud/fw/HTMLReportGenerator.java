package Utilities.cloud.fw;

import DataModel.CONST.*;
import Utilities.cloud.rest.RestCallCache;
import com.hp.gagawa.java.elements.*;

import java.util.List;

public class HTMLReportGenerator {

    private static Html html;
    private static Body body;
    private static Head head;
    private static Div testCasesDiv;
    private static volatile HTMLReportGenerator instance = null;

    public static HTMLReportGenerator getInstance() {
        if (instance == null) {
            synchronized (HTMLReportGenerator.class) {
                if (instance == null) {
                    instance = new HTMLReportGenerator();
                }
            }
        }
        return instance;
    }

    public void setTitle(String titleText){
        Title title= new Title();
        title.appendText(titleText);
        head.appendChild(title);
    }

    public String write(){
        return html.write();
    }

    public void setStyle(){
        Link link = new Link();
        link.setRel("stylesheet");
        link.setHref("style.css");
        head.appendChild(link);
    }

    public void addHead(){
        html.appendChild(head);
    }

    public void addBody(){
        html.appendChild(body);
    }

    public void addTestCasesDiv(){
        body.appendChild(testCasesDiv);
    }

    public HTMLReportGenerator(){
        html = new Html();
        body = new Body();
        head = new Head();
        testCasesDiv = new Div();
        Script script = new Script("application/javascript");
        script.appendText("function toggle(id) {var x = document.getElementById(id);if (x.style.display === \"none\" || x.style.display=== \"\") {x.style.display = \"block\";} else {x.style.display = \"none\";}}");
        body.appendChild(script);
    }

    public void addTestCase(TestCaseExecution testCaseExecution, int caseNumber){
        String caseDivId = "TestCase"+caseNumber;
        Div testCaseDiv = createTestCaseDiv(caseDivId);

        P p = createCaseNameP(testCaseExecution, caseDivId);
            testCaseDiv.appendChild(p);

        Div stepsDiv = createStepsDiv(caseDivId);

        for(int i = 1; i <= testCaseExecution.getTestSteps().size(); i++){
            stepsDiv.appendChild(createStepDiv(testCaseExecution.getTestSteps().get(i-1),caseDivId, i));
        }

        testCaseDiv.appendChild(stepsDiv);
        testCasesDiv.appendChild(testCaseDiv);
    }

    public void addTestCase(){
        TestCaseExecution testCaseExecution = RestCallCache.getInstance().getCurrentTestCaseExecution();
        int caseNumber = RestCallCache.getInstance().getCurrentCaseNumber();

        String caseDivId = "TestCase"+caseNumber;
        Div testCaseDiv = createTestCaseDiv(caseDivId);

        P p = createCaseNameP(testCaseExecution, caseDivId);
        testCaseDiv.appendChild(p);

        Div stepsDiv = createStepsDiv(caseDivId);

        for(int i = 1; i <= testCaseExecution.getTestSteps().size(); i++){
            stepsDiv.appendChild(createStepDiv(testCaseExecution.getTestSteps().get(i-1),caseDivId, i));
        }

        testCaseDiv.appendChild(stepsDiv);
        testCasesDiv.appendChild(testCaseDiv);
    }

    private Div createStepDiv(TestStep step, String caseDivId, int stepNumber){
        Div stepDiv = new Div();
        stepDiv.setCSSClass("TestStep");
        stepDiv.setId(caseDivId+"TestStep"+stepNumber);
        List<APICallLog> callLogs = step.getTestStepCallList();
        //List<String> extraMsgs = step.getTestStepExtraMessageList();
        // Step name
        A a = new A();
            a.appendText(step.getTestStepName());
            //a.setHref("#");
            a.setAttribute("onclick","toggle('"+ stepDiv.getId() + "CallsAndOutputs"+"')");
            if(callLogs.size() > 0){
                a.setCSSClass("circle");
            }
        stepDiv.appendChild(a);

            Div callsAndOutputs = createAPICallsAndOutputDiv(stepDiv.getId());
            Div calls = createAPICallsDiv(stepDiv.getId());
            Div outputs = createAPIStepOutputDiv(stepDiv.getId());

            step.getOutputs().forEach((x,y)-> {
                        outputs.appendChild(new P().appendText(x + ": " + y));
                    }
            );

            if(callLogs.size() > 0){
                for(int i=0; i < callLogs.size(); i++){
                    calls.appendChild(createAPICallDiv(calls.getId(), i+1, callLogs.get(i) ));
                }
            }

        callsAndOutputs.appendChild(outputs);
        callsAndOutputs.appendChild(calls);
        //stepDiv.appendChild(calls);
        stepDiv.appendChild(callsAndOutputs);
        stepDiv.appendChild(createDotLineDiv());

        return stepDiv;
    }

    private Div createDotLineDiv(){
        Div div = new Div();
        div.setCSSClass("dotline");
        return div;
    }

    private Div createStepsDiv(String caseDivId){
        Div stepsDiv = new Div();
        stepsDiv.setCSSClass("TestSteps");
        stepsDiv.setId(caseDivId  +"Steps");
        return stepsDiv;
    }

    private Div createAPICallsDiv(String stepId){
        Div callDiv = new Div();
        callDiv.setCSSClass("APICalls");
        callDiv.setId(stepId + "Calls");
        return callDiv;
    }

    private Div createAPIStepOutputDiv(String stepId){
        Div outputDiv = new Div();
        outputDiv.setCSSClass("Output");
        outputDiv.setId(stepId+"Outputs");
        return outputDiv;
    }

    private Div createAPICallsAndOutputDiv(String stepId){
        Div callsAndOutputDiv = new Div();
        callsAndOutputDiv.setCSSClass("CallsAndOutputs");
        callsAndOutputDiv.setId(stepId+"CallsAndOutputs");
        return callsAndOutputDiv;
    }

    private Div createAPICallDiv(String APICallsId, int callNumber, APICallLog apiCallLog ){
        Div callDiv = new Div();
        callDiv.setCSSClass("APICall");
        callDiv.setId(APICallsId + "Call" + callNumber);

        A a = new A();
        a.appendText(apiCallLog.getMethod() + "    " + apiCallLog.getName() + "    " + apiCallLog.getStatus());

        a.setAttribute("onclick","toggle('"+ callDiv.getId() + "Details');");
        a.setCSSClass("circle2");
        callDiv.appendChild(a);
        callDiv.appendChild(createAPICallDetailsDiv(callDiv.getId(), apiCallLog.getMessage() ));
        return callDiv;
    }

    private Div createAPICallDetailsDiv(String APICallId, String message){
        Div callDetails = new Div();
        callDetails.setCSSClass("APICallDetails");
        callDetails.setId(APICallId + "Details");

        Pre pre = new Pre();
        pre.appendText(message);

        callDetails.appendChild(pre);
        return callDetails;
    }


    private Div createTestCaseDiv(String caseDivId){

        Div testCaseDiv = new Div();
        testCaseDiv.setCSSClass("TestCase");
        testCaseDiv.setId(caseDivId);
        return testCaseDiv;
    }

    private P createCaseNameP(TestCaseExecution testCaseExecution, String caseDivId){
        A a = new A();
        a.setCSSClass("CaseName");
        a.setAttribute("onclick","toggle('"+ caseDivId  +"Steps')");
        //a.setHref("#");
        a.appendText(testCaseExecution.getTestName()); //+ " Iteration: " + testCaseExecution.getIteration());
        Span iteration = new Span();
            iteration.setCSSClass("iteration");
            iteration.appendText(String.valueOf(testCaseExecution.getIteration()));

        Span span = new Span();
        String res = testCaseExecution.getTestResult().firstLetterCapitalizedName();
            span.appendText(res);
            span.setCSSClass(res);

        P p = new P();
            p.appendChild(a);
            p.appendChild(iteration);
            p.appendChild(span);
        return p;
    }

}
