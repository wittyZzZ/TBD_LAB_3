import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AboutView from '@/views/AboutView.vue'
import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'
import ProductsView from '@/views/ProductsView.vue'
import ClientView from '@/views/ClientView.vue'
import EditProductView from '@/views/EditProductView.vue'
import DetailsProductView from '@/views/DetailsProductView.vue'
import PaymentView from '@/views/PaymentView.vue'
import NotFoundView from '@/views/NotFoundView.vue'
import OrdersView from '@/views/OrdersView.vue'
import OrdersByStore from '@/views/OrdersByStore.vue'
import DeliveryBy30 from '@/views/DeliveryBy30.vue'
import DeliveryByZone from '@/views/DeliveryByZone.vue'
import ClientsByRadius from '@/views/ClientsByRadius.vue'
import ClientList from '@/views/ClientList.vue'
import ClientOrders from '@/views/ClientOrders.vue'
import OrderDetails from '@/views/OrderDetails.vue'
import TestMapbox from '@/views/TestMapbox.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView,
    meta: { requiresAuth: true } 
  },
  {
    path: '/about',
    name: 'About',
    component: AboutView,
    meta: { requiresAuth: true } 
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginView
  },
  {
    path: '/register',
    name: 'Register',
    component: RegisterView 
  },
  {
    path: '/products',
    name: 'Products',
    component: ProductsView,
    meta: { requiresAuth: true } 
  },
  {
    path: '/client/:id',
    name: 'Client',
    component: ClientView,
    meta: { requiresAuth: true } 
  },
  {
    path: '/editproduct',
    name: 'EditProduct',
    component: EditProductView,
    meta: { requiresAuth: true } 
  },
  {
    path: '/detailsproduct/:id',
    name: 'DetailsProduct',
    component: DetailsProductView,
    props: true,
    meta: { requiresAuth: true } 
  },
  {
    path: '/payment',
    name: 'PaymentView',
    component: PaymentView,
    meta: { requiresAuth: true } 
  },
  {
    path: '/orders',
    name: 'OrdersView',
    component: OrdersView
  },
  {
    path: '/delivery30',
    name: 'DeliveryBy30',
    component: DeliveryBy30
  },
  {
    path: '/orderstore',
    name: 'OrdersByStore',
    component: OrdersByStore
  },
  {
    path: '/deliveryzone',
    name: 'DeliveryByZone',
    component: DeliveryByZone
  },
  {
    path: '/clientsradius',
    name: 'ClientsByRadius',
    component: ClientsByRadius
  },
  {
    path: '/clientlist',
    name: 'ClientList',
    component: ClientList
  },
  {
    path: '/clientorders',
    name: 'ClientOrders',
    component: ClientOrders
  },
  {
    path: '/orderdetails',
    name: 'OrderDetails',
    component: OrderDetails
  },
  {
    path: '/test-mapbox',
    name: 'TestMapbox',
    component: TestMapbox
  },
  {
    path: '/:pathMatch(.*)*', // Ruta catch-all
    name: 'NotFound',
    component: NotFoundView,
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

// Guard de navegación global
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token'); // Obtenemos el token del localStorage
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth); // Verificamos si la ruta requiere autenticación

  if (requiresAuth && !token) {
    // Si la ruta requiere autenticación y no hay token, redirige al login
    next({ name: 'Login' });
  } else {
    // Si no requiere autenticación o el token está presente, continúa
    next();
  }
});

export default router
