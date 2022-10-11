
import { environment as env } from '../../environments/environment';

export const AuthConstant = {
    'generateTokenUrl': `${env.apiUrl}/token/generate-token`,
    'logout': `${env.apiUrl}/user/logout`,
    'userProfile': `${env.apiUrl}/profile`,
    'signup': `${env.apiUrl}/user/signup`,
    'forgetPassword': `${env.apiUrl}/user/forgetPassword`,
    'resetPassword': `${env.apiUrl}/user/resetPassword`
  };
