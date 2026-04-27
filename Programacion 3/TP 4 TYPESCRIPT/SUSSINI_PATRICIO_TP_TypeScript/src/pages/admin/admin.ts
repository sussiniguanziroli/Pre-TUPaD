import { logoutUser } from '../../utils/auth';
import { navigateTo } from '../../utils/navigate';

const btn = document.getElementById('logoutBtn') as HTMLButtonElement;

btn.addEventListener('click', () => {
    logoutUser();
    navigateTo('/src/pages/auth/login/index.html');
});