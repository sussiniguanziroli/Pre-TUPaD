import { defineConfig } from 'vite';
import { resolve } from 'path';

export default defineConfig({
  build: {
    rollupOptions: {
      input: {
        index: resolve(__dirname, 'index.html'),
        authLogin: resolve(__dirname, 'src/pages/auth/login/login.html'),
        authRegister: resolve(__dirname, 'src/pages/auth/register/register.html'),
        storeHome: resolve(__dirname, 'src/pages/store/home/home.html'),
        storeProductDetail: resolve(__dirname, 'src/pages/store/productDetail/productDetail.html'),
        storeCart: resolve(__dirname, 'src/pages/store/cart/cart.html'),
        clientOrders: resolve(__dirname, 'src/pages/client/orders/orders.html'),
        adminHome: resolve(__dirname, 'src/pages/admin/adminHome/adminHome.html'),
        adminCategories: resolve(__dirname, 'src/pages/admin/categories/categories.html'),
        adminProducts: resolve(__dirname, 'src/pages/admin/products/products.html'),
        adminOrders: resolve(__dirname, 'src/pages/admin/orders/orders.html'),
      },
    },
  },
  base: './',
});
