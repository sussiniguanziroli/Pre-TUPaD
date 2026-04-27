import { defineConfig } from 'vite'
import { resolve } from 'path'
import { fileURLToPath } from 'url'

const __dirname = fileURLToPath(new URL('.', import.meta.url))

export default defineConfig({
  build: {
    rollupOptions: {
      input: {
        main: resolve(__dirname, 'index.html'),
        login: resolve(__dirname, 'src/pages/auth/login/index.html'),
        registro: resolve(__dirname, 'src/pages/auth/registro/index.html'),
        admin: resolve(__dirname, 'src/pages/admin/index.html'),
        client: resolve(__dirname, 'src/pages/client/index.html')
      }
    }
  }
})