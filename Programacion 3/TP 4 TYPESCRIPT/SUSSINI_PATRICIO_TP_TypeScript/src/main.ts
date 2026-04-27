import { isAuthenticated, hasRole } from './utils/auth';
import { Rol } from './types/Rol';
import { navigateTo } from './utils/navigate';

const path = window.location.pathname;

if (path.includes('/admin/')) {
    if (!isAuthenticated()) {
        navigateTo('/src/pages/auth/login/index.html');
    } else if (!hasRole(Rol.ADMIN)) {
        navigateTo('/src/pages/client/index.html');
    }
}

if (path.includes('/client/')) {
    if (!isAuthenticated()) {
        navigateTo('/src/pages/auth/login/index.html');
    } else if (!hasRole(Rol.CLIENT)) {
        navigateTo('/src/pages/admin/index.html');
    }
}