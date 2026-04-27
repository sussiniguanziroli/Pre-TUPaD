import { getUsers, loginUser } from '../../../utils/auth';
import { navigateTo } from '../../../utils/navigate';
import { Rol } from '../../../types/Rol';

const form = document.getElementById('loginForm') as HTMLFormElement;

form.addEventListener('submit', (e) => {
    e.preventDefault();
    const email = (document.getElementById('email') as HTMLInputElement).value;
    const password = (document.getElementById('password') as HTMLInputElement).value;

    const users = getUsers();
    const user = users.find(u => u.email === email && u.password === password);

    if (user) {
        const userData = { email: user.email, rol: user.rol };
        loginUser(userData);
        if (user.rol === Rol.ADMIN) {
            navigateTo('/src/pages/admin/index.html');
        } else {
            navigateTo('/src/pages/client/index.html');
        }
    } else {
        alert('Credenciales incorrectas');
    }
});