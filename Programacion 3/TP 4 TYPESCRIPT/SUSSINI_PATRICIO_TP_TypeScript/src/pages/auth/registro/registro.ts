import { saveUser, getUsers } from '../../../utils/auth';
import { Rol } from '../../../types/Rol';
import { navigateTo } from '../../../utils/navigate';

const form = document.getElementById('registroForm') as HTMLFormElement;

form.addEventListener('submit', (e) => {
    e.preventDefault();
    const email = (document.getElementById('email') as HTMLInputElement).value;
    const password = (document.getElementById('password') as HTMLInputElement).value;

    const users = getUsers();
    const exists = users.some(u => u.email === email);

    if (!exists) {
        saveUser({ email, password, rol: Rol.CLIENT });
        navigateTo('/src/pages/auth/login/index.html');
    } else {
        alert('Usuario existente');
    }
});