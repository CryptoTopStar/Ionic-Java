
import { environment as env } from '../environments/environment';

const UserUrl = {
    'getAllRoles': `${env.apiUrl}/user/findAllRoles`,
    'getAllUsers': `${env.apiUrl}/user/findAll`,
    'addUser': `${env.apiUrl}/user/addUser`,
    'enableUser': `${env.apiUrl}/user/enable`,
    'search': `${env.apiUrl}/user/search`,
    'deleteUser': `${env.apiUrl}/user/deleteUser/`,
    'updateProfile': `${env.apiUrl}/user/updateProfile/`,
    'uploadProfileImage': `${env.apiUrl}/uploadUserProfileImage/`,
};

const AuthConstant = {
  'generateTokenUrl': `${env.apiUrl}/token/generate-token`,
  'logout': `${env.apiUrl}/user/logout`,
  'userProfile': `${env.apiUrl}/profile`,
  'signup': `${env.apiUrl}/user/signup`,
  'forgetPassword': `${env.apiUrl}/user/forgetPassword`,
  'resetPassword': `${env.apiUrl}/user/resetPassword`
};

export { UserUrl, AuthConstant }
