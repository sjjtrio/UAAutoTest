package Utilities;

public class DataKeys {
    public class Organization{
        public static final String AllOrgNames="All.Organization.Names";
        public static final String AllTokenIDMOrgNames="All.TokenIDM.Organization.Names";
        public static final String OrgModelSelected = "Org.Model.Selected";
        public static final String JBillingInfo = "JBilling.Info";
        public static final String JBillingScraped = "JBilling.Scraped";
        public static final String RWSInfo = "RWS.Info";
        public static final String RWSScraped = "RWS.Scraped";
        public static final String SMTPInfo = "SMTP.Info";
        public static final String SMTPScraped = "SMTP.Scraped";
        public static final String OrgNameCreated = "Org.Name.Created";
        public static final String OrgNameClicked = "Org.Name.Clicked";
        public static final String OrgModelClicked = "Org.Model.Clicked";
        public static final String OrgNameSelected = "Org.Name.Selected";
    }

    public class Common{
        public static final String EmailExt = "gmail.com";
        public static final String ServiceAccountDefaultCustomer = "Default";
        public static final String RoleNamePrefix = "AT_ROLE_";
        public static final String ScenarioDomainCreated = "Scenario.Domain.Created";
        public static final String ScenarioProjectCreated = "Scenario.Project.Created";
        public static final String ScenarioUserCreated = "Scenario.User.Created";
        public static final String DomainRoleAssigned = "Scenario.Role.Assigned.Domain";
        public static final String ProjectRoleAssigned = "Scenario.Role.Assigned.Project";
    }

    public class Compartment{
        public static final String CompartmentsScraped = "Compartments.Scraped";
        public static final String CompartmentsListed = "Compartments.Listed";
        public static final String CompartmentNamePrefix= "AT_Comp_";
        public static final String CompartmentCreated = "Compartment.Created";
    }

    public class Role{
        public static final String OrgRoleScraped = "Organization.Role.Scraped";
        public static final String SelectedRoleName = "Selected.Role.Name";
        public static final String RoleRelatedOrgs = "Role.Related.Orgs";
        public static final String RolePermissionKeysSelected = "Role.Permission.Keys.Selected";
        public static final String RoleCreated = "Role.Created";
    }

    public class User{
        public static final String CurrentUser = "Current.Login.User";
    }

    public class Permissions{
        public static final String ProviderKey = "Provider.Permissions.Keys";
        public static final String ProviderReadableNames = "Provider.Permissions.Readable.Names";
        public static final String ProviderPermissionPairList = "Provider.Permissions.Pair.List";
        public static final String PermissionsKeysAlreadyAssignedToOrg = "Permissions.Keys.Already.Assigned.To.ORG";
        public static final String PermissionsReadableAlreadyAssignedToOrg = "Permissions.Readable.Already.Assigned.To.ORG";
        public static final String PermissionsKeysAssignableToOrg = "Permissions.Keys.Assignable.To.ORG";
        public static final String PermissionsReadablesAssignableToOrg = "Permissions.Readable.Assignable.To.ORG";
        public static final String PermissionsModelToBeAdded = "Permissions.Model.To.Be.Added";
        public static final String PermissionsModelToBeUsed = "Permissions.Model.To.Be.Used";
        public static final String PermissionsItemList = "Permissions.Item.List";
        public static final String PermissionsModelCreated = "Permissions.Model.Created";
    }

    public class Templates{
        public static final String TemplateCreated = "Templates.Created";

    }

    public class ServiceAccount{
        public static final String ServiceAccountToCreate =  "ServiceAccountModel.To.Create";
        public static final String ServiceAccountCreated = "ServiceAccountModel.Created";
        public static final String ServiceAccountScraped = "ServiceAccountModel.Scraped";
        public static final String ServiceAccountAdmins = "ServiceAccountModel.Admins";
        public static final String ServiceAccountPSWComplianceType = "ServiceAccountModel.Password.Compliance.Type";
    }

    public class UserProfile{
        public static final String UserProfileOrgs = "UserProfile.Organization.List";
        public static final String UserProfileScraped = "UserProfile.Scraped";
        public static final String UserProfileSelectedOrg = "UserProfile.Selected.Org";
        public static final String UserProfileSelectedUserModel= "UserProfile.Selected.UserModel";
    }

    public class Logs{
        public static final String LogsTableHeaderFilterInputValue = "Logs.Table.Filter.Input.Value";
        public static final String LogsTableHeaderFilterSelectValue = "Logs.Table.Filter.Select.Value";
        public static final String LogsTableHeaderFilterSearchValue = "Logs.Table.Filter.Search.Value";
        public static final String LogsTableHeaderSelected= "Logs.Table.Header.Selected";
    }

    public class Dashboard{
        public static final String DashboardItems = "Current.Dashboard.Items";
    }

    public static String combineKeys(String key1, String key2){
        return key1+"."+key2;
    }

    public class API{
        public static final String APIDefaultGetRequest = "API.Default.Get.Request";
        public static final String APIDefaultPostRequest = "API.Default.Post.Request";
        public static final String APIDefaultDeleteRequest = "API.Default.Delete.Request";
        public static final String APIDefaultPatchRequest = "API.Default.Patch.Request";
        public static final String APIDefaultNonAdminAuthReuqest = "API.Default.Non.Admin.Auth.Request";
        public static final String APIRequestBodyJson ="API.Request.Body.Object.Json";
        public static final String APIDomainsGet = "API.Domains.Get";
        public static final String APIAllDomainsGet = "API.All.Domains.Get";
        public static final String APIAuthDefault = "API.Auth.Default";
        public static final String APIAuthVPCCustomer = "API.Auth.VPC.Customer";
        public static final String APIAuthCustomer= "API.Auth.Customer";
        public static final String APIAuthScopeDomain = "API.Auth.Scope.Domain";
        public static final String APIAuthScopeProject = "API.Auth.Scope.Project";
        public static final String APIAuthValidatePassword = "API.Auth.Validate.Password";
        public static final String APIAuthExtras = "API.Auth.Extras";
        public static final String APIAuthDCHeader = "API.Auth.DC.Header";
        public static final String APIAuthNewUser = "API.Auth.New.User";
        public static final String APIUsersList = "API.Users.List";
        public static final String APIRolesList = "API.Roles.List";
        public static final String APIPickedUser="API.Picked.User";
        public static final String APIPickedUserRole="API.Picked.User.Role";
        public static final String APIPickedProjectID="API.Picked.Project.ID";
        public static final String APIPickedDomainID="API.Picked.Domain.ID";
        public static final String APIValidateToken = "API.Validate.Token";
        public static final String APIValidateTokenResponse = "API.Validate.Token.Response";
        public static final String APIUserResetPassword="API.User.Reset.Password";
        public static final String APIUserUpdatePassword="API.User.Update.Password";
        public static final String APIUserOldPassword="API.User.Old.Password";
        public static final String APIUserNewPassword="API.User.New.Password";
        public static final String APIUserCurrentPassword="API.User.Current.Password";
        public static final String APIProjectDetails = "API.Project.Details";
        public static final String APIAllProjectsGet = "API.All.Projects.Get";
        public static final String APIRoleDomainAssignment = "API.Domain.Assignments.Role";
        public static final String APIRoleProjectAssignment = "API.Project.Assignments.Role";
    }

    public class API_Body{
        public static final String API_DOMAIN_ID = "API.Domain.ID";
        public static final String API_DOMAIN_NAME = "API.Domain.NAME";
        public static final String API_DOMAIN_BODY_JSON = "API.Domain.Body.Json";
        public static final String API_PROJECT_ID = "API.Project.ID";
        public static final String API_PROJECT_ID_LIST = "API.Project.ID.List";
        public static final String API_PROJECT_NAME_LIST = "API.Project.Name.List";
        public static final String API_USER_ID = "API.User.ID";
        public static final String API_USER_BODY_JSON = "API.User.Body.Json";
        public static final String API_ROLE_ID = "API.Role.ID";
        public static final String API_ROLE_ID_LIST = "API.Role.ID.List";
        public static final String API_ROLE_ASSIGNMENTS ="API.Role.Assignments";
        public static final String API_POLICY_LIST ="API.Policy.List";
        public static final String API_POLICY_MAPPING ="API.Policy.Mapping";
        public static final String API_POLICY_ENDPOINT ="API.Policy.Endpoint";
        public static final String API_SERVICE_ID = "API.Service.ID";
        public static final String API_ENDPOINT_ID = "API.Endpoint.ID";
        public static final String API_GROUP_ID = "API.Group.ID";
    }

    public class API_Header{
        public static final String API_DC_Name = "API.DC.Name";
    }

    public class API_Jira{
        public static final String API_JIRA_REQUEST = "API.JIRA.REQUEST";
    }

    public class API_Object{
        public static final String API_Domain_Object = "API_Domain_Object";
        public static final String API_Domain_List_Object = "API_Domain_List_Object";
        public static final String API_Project_Object = "API_Project_Object";
        public static final String API_Project_List_Object = "API_Project_List_Object";
        public static final String API_User_Object = "API_User_Object";
        public static final String API_User_List_Object = "API_User_List_Object";
        public static final String API_Role_Object = "API_Role_Object";
        public static final String API_Role_List_Object = "API_Role_List_Object";
        public static final String API_Services_List_Object = "API_Services_List_Object";
        public static final String API_Endpoints_List_Object = "API_Endpoints_List_Object";
        public static final String API_Temp_User_Object = "API_TEMP_User_Object";
    }

    public static class API_ID{
        public static String API_ID(String key){
            return "API_ID_"+ key;
        }

        public static String service_id(){return API_ID("service_id");}

        public static String service_id_for_endpoint(){return API_ID("service_id_for_endpoint");}

        public static String endpoint_id(){
            return API_ID("endpoint_id");
        }

        public static String domain_id(){
            return API_ID("domain_id");
        }

        public static String project_id(){
            return API_ID("project_id");
        }

        public static String user_id(){
            return API_ID("user_id");
        }
        public static String user_id_for_others(){
            return API_ID("user_id_1");
        }

        public static String group_id(){
            return API_ID("group_id");
        }

        public static String role_id(){
            return API_ID("role_id");
        }

        public static String implied_role_id(){
            return API_ID("implied_role_id");
        }

        public static String prior_role_id(){
            return API_ID("prior_role_id");
        }

    }

    public class ServiceInfoAPI {
        public static final String Last_Call_Path_Parameters = "Last_Call_Path_Parameters";
        public static final String VPC_Auth_Token_Without_Extra = "ServiceInfo_VPC_Auth_Without_Extra";
        public static final String VPC_Auth_Token_With_Extra = "ServiceInfo_VPC_Auth_With_Extra";
        public static final String NGP_Auth_Token = "ServiceInfo_NGP_Auth_Token";
        public static final String Service_Info_Query_Parameters = "ServiceInfo_Query_Parameters";
        public static final String Current_User_Model = "ServiceInfo_Current_User_Model";
        public static final String Service_Info_Test_User_Name_Key = "ServiceInfo_Test_User";
        public static final String Service_Info_Test_Department_Name_Key = "ServiceInfo_Test_Department";
        public static final String Service_Info_Test_Customer_Name_Key = "ServiceInfo_Test_Customer";
        public static final String Service_Info_Test_Resource_Name_Key = "ServiceInfo_Test_Resource";
        public static final String Service_Info_Test_Location_Name_Key = "ServiceInfo_Test_Location";
        public static final String Service_Info_Test_Offering_Name_Key = "ServiceInfo_Test_Offering";
        public static final String Service_Info_User_List = "ServiceInfo_User_List";
        public static final String Service_Info_User_Object = "ServiceInfo_User_Object";
        public static final String Service_Info_Resource_List = "ServiceInfo_Resource_List";
        public static final String Service_Info_Resource_Object = "ServiceInfo_Resource_Object";
        public static final String Service_Info_Department_Object = "ServiceInfo_Department_Object";
        public static final String Service_Info_Customer_Object = "ServiceInfo_Customer_Object";
        public static final String Service_Info_Privilege_Object = "ServiceInfo_Privilege_Id";
        public static final String Service_Info_Query_parameters = "ServiceInfo_Query_Parameters";

    }


}

