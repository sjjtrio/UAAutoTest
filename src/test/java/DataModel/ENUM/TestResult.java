package DataModel.ENUM;

public enum TestResult {
    Passed("PASSED"),
    Failed("FAILED"),
    Warning("WARNING");

    private String res;

    TestResult(String e){res = e;}

    public String getRes(){return res;}
}
