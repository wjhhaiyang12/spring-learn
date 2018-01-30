package cannotscan;

import org.springframework.stereotype.Component;

public class CannotAutoScan {

    private int injectVal;

    private InjectTest injectTest;

    public CannotAutoScan(){
        System.out.println("initalize cannot auto scan class");
    }

    public void afterInitialization(){
        System.out.println("bean initalize complete " + "injectVal:" + injectVal);
        System.out.println("bean initalize complete " + "injectBean-name:" + injectTest.getName());
    }

    public int getInjectVal() {
        return injectVal;
    }

    public void setInjectVal(int injectVal) {
        this.injectVal = injectVal;
    }

    public InjectTest getInjectTest() {
        return injectTest;
    }

    public void setInjectTest(InjectTest injectTest) {
        this.injectTest = injectTest;
    }
}
