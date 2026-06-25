import { getSesion, login, rutaHomePorRol } from '../../../utils/auth';

// Si ya hay sesión activa, no tiene sentido mostrar el login de nuevo.
const sesionActual = getSesion();
if (sesionActual) {
  location.replace(rutaHomePorRol(sesionActual.rol));
}

const form = document.getElementById('login-form') as HTMLFormElement;
const alertBox = document.getElementById('alert-box') as HTMLDivElement;

function mostrarError(mensaje: string): void {
  alertBox.innerHTML = `<div class="form-error">${mensaje}</div>`;
}

form.addEventListener('submit', async (e) => {
  e.preventDefault();
  alertBox.innerHTML = '';

  const mail = (document.getElementById('mail') as HTMLInputElement).value.trim();
  const password = (document.getElementById('password') as HTMLInputElement).value;

  if (!mail || !password) {
    mostrarError('Completá email y contraseña.');
    return;
  }

  try {
    const usuario = await login(mail, password);
    location.href = rutaHomePorRol(usuario.rol);
  } catch (err) {
    mostrarError(err instanceof Error ? err.message : 'Error al iniciar sesión.');
  }
});
