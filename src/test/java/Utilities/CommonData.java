package Utilities;

public class CommonData {
    public static final String KEY_PROP_RANDOM_PREFIX = "TS_AT_";

    public class Permissions{
        public static final String ServiceAccountAdminReadableName = "SERVICE_ACCOUNT_ADMIN";
        public static final String ServiceAccountAdminKeyName = "SERVICE_ACCOUNT_ADMIN";
        public static final String SuperIdmAdminReadableName = "Global IdM Administrator";
        public static final String SuperIdmAdminKeyName = "SUPER_IDM_ADMIN";
    }

    public class OrgDetailPageNav{
        public static final String ROLES = "Roles";
        public static final String CONFIGURATIONS = "CONFIGURATIONS";
    }

    public class DXCUserTable{
        public static final int UserNameCol = 2;
        public static final int RelateRoleButtonCol = 3;
    }

    public class Template{
        public static final String ProviderTypeTemplateName = "AT_ProviderTemplate";
        public static final String ConsumerTypeTemplateName = "AT_ConsumerTemplate";
        public static final String MixTypeTemplateName = "AT_MixTemplate";
        public static final String AccountAdminTemplateName = "AT_TheAccountAdminTemplate";
    }

    public class Common{
        public static final String AdminOrg = "MissionControl";
        public static final String Prefix = "AT_";
        public static final String SwitchOrgRoleName = "SwitchOrgRole";
        public static final String CommonRoleName = "AutomationRole";
        public static final String PolicyChangeDomainName = "AT_Policy_Test_Domain";
        public static final String PolicyChangeProjectName = "AT_Policy_Test_Project";
        public static final String PolicyChangeUserName = "AT_Policy_Test_User";
        public static final String PolicyChangeUserPsw = "I2g*12Kwnvx$530S2";
        public static final String PolicyChangeUserEmail = "policychangetest@gmail.com";

        public static final String PolicyChangeTempDomainName = "TS_AT_Policy_Temp_Test_Domain";
        public static final String PolicyChangeTempProjectName = "TS_AT_Policy_Temp_Test_Project";
        public static final String PolicyChangeTempUserName = "TS_AT_Policy_Temp_Test_User";
        public static final String PolicyChangeTempUserPsw = "I2g*12Kwnvx$530S2";
        public static final String PolicyChangeTempUserPswChange = "I2g*12Kwnvx$530S2";
        public static final String PolicyChangeTempUserEmail = "policychangetemptest@gmail.com";
        public static final String PolicyChangeTempGroup = "TS_AT_Policy_Temp_Test_Group";
        public static final String PolicyChangeTempRole = "TS_AT_Policy_Temp_Test_Role";
        public static final String PolicyChangeTempImpliesRole = "TS_AT_Policy_Temp_Test_Implies_Role";
        public static final String PolicyChangeTempPriorRole = "TS_AT_Policy_Temp_Test_Prior_Role";
        public static final String PolicyChangeTempService = "TS_AT_Policy_Temp_Test_Service";
        public static final String PolicyChangeTempEndpoint = "http://www.endpointtest.com";

        public static final String PolicyChangeTempDeleteDomainName = "TS_AT_Policy_Temp_DeleteTest_Domain";
        public static final String PolicyChangeTempDeleteProjectName = "TS_AT_Policy_Temp_DeleteTest_Project";
        public static final String PolicyChangeTempDeleteUserName = "TS_AT_Policy_Temp_DeleteTest_User";
        public static final String PolicyChangeTempDeleteUserName1 = "TS_AT_Policy_Temp_DeleteTest_User1";
        public static final String PolicyChangeTempDeleteUserPsw = "I2g*12Kwnvx$530S2";
        public static final String PolicyChangeTempDeleteUserPswChange = "I2g*12Kwnvx$530S2";
        public static final String PolicyChangeTempDeleteUserEmail = "policychangetempDeletetest@gmail.com";
        public static final String PolicyChangeTempDeleteGroup = "TS_AT_Policy_Temp_DeleteTest_Group";
        public static final String PolicyChangeTempDeleteRole = "TS_AT_Policy_Temp_DeleteTest_Role";
        public static final String PolicyChangeTempDeleteImpliesRole = "TS_AT_Policy_Temp_DeleteTest_Implies_Role";
        public static final String PolicyChangeTempDeletePriorRole = "TS_AT_Policy_Temp_DeleteTest_Prior_Role";
        public static final String PolicyChangeTempDeleteService = "TS_AT_Policy_Temp_DeleteTest_Service";
        public static final String PolicyChangeTempDeleteService1 = "TS_AT_Policy_Temp_DeleteTest_Service1";
        public static final String PolicyChangeTempDeleteEndpoint = "http://www.endpointtest.com";

    }

    public static class UserNames{
        public static String PolicyTestUserName(String name){
            return KEY_PROP_RANDOM_PREFIX + "user_" + name;
        }
    }
}
