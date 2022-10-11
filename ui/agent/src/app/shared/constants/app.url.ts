
import { environment as env } from '../../../environments/environment';

const UserUrl = {
    'getAllRoles': `${env.apiUrl}/user/findAllRoles`,
    'getAllUsers': `${env.apiUrl}/user/findAll`,
    'addUser': `${env.apiUrl}/user/addUser`,
    'enableUser': `${env.apiUrl}/user/enable`,
    'search': `${env.apiUrl}/user/search`,
    'deleteUser': `${env.apiUrl}/user/deleteUser/`,
  };

const TemplateUrl = {
  'searchDeviceTemplate': `${env.apiUrl}/deviceTemplate/search`,
  'searchControlTemplate':  `${env.apiUrl}/controlTemplate/search`,
  'addDeviceTemplate': `${env.apiUrl}/deviceTemplate/addDevice`,
  'addControlTemplate': `${env.apiUrl}/controlTemplate/addControl`,
  'deleteControlTemplate': `${env.apiUrl}/controlTemplate/delete/`,
  'deleteDeviceTemplate': `${env.apiUrl}/deviceTemplate/deleteDevice/`,
  'enableControlTemplate': `${env.apiUrl}/controlTemplate/enable/`,
  'enableDeviceTemplate': `${env.apiUrl}/deviceTemplate/enable/`,
};

const deviceUrl = {
  'search': `${env.apiUrl}/device/search`,
  'mapDevice': `${env.apiUrl}/device/mapDeviceTempalte`,
  'delete': `${env.apiUrl}/device/delete/`,
  'enable': `${env.apiUrl}/device/enable/`,
  'changeDeviceName': `${env.apiUrl}/device/updateName`
};

const controlUrl = {
  'search': `${env.apiUrl}/control/search`,
  'changeState': `${env.apiUrl}/control/changeState`
};


export { UserUrl, TemplateUrl, deviceUrl, controlUrl }





