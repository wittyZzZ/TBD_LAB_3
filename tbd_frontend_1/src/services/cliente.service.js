import httpClient from "../http-common"

const getAll = () => {
  return httpClient.get('/api/cliente/');
};

const getById = id => {
  return httpClient.get(`/api/cliente/${id}`);
};

const getInRadius = (id_tienda_input, radius_km) => {
  return httpClient.get("/api/cliente/inradius", {
    params: { id_tienda_input, radius_km }
  });
};

export default { getAll, getById, getInRadius };




