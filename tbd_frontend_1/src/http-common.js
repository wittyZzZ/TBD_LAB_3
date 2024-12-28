import axios from "axios";

// Crea una instancia de Axios
const httpClient = axios.create({
  baseURL: "http://localhost:8080", // Cambia esto por la URL de tu backend
  // baseURL: "http://localhost:8081", // Cambia esto por la URL de tu backend
  headers: {
    "Content-type": "application/json",
  },
});

// Interceptor para incluir el token en las cabeceras de todas las solicitudes
httpClient.interceptors.request.use(config => {
      // Obtén el token de localStorage
      const token = localStorage.getItem("token");
      if (token) {
        // Agrega el token al header Authorization
        config.headers.Authorization = token;
      }
      return config;
    },
    (error) => {
      // Manejo de errores antes de enviar la solicitud
      return Promise.reject(error);
    }
);

httpClient.interceptors.response.use(
  (response) => {
    // Si la respuesta es exitosa, simplemente retornarla
    return response;
  },
  (error) => {
    // Si hay un error en la respuesta
    if (error.response && error.response.status === 403) {
      console.error("Token expirado o inválido. Redirigiendo a login.");
      
      // Eliminar el token y carrito del almacenamiento local
      localStorage.removeItem("token");
      localStorage.removeItem("carrito");
      // Redirigir al login
      window.location.href = "/login";
    }

    // Retornar el error para que pueda manejarse en otra parte si es necesario
    return Promise.reject(error);
  }
);

export default httpClient;