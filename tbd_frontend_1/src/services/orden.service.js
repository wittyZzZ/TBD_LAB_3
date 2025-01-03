import httpClient from "../http-common"

const getAll = () => {
  return httpClient.get('/api/orden/');
};

const getById = id => {
  return httpClient.get(`/api/orden/${id}`);
};

const getByClientId = id => {
  return httpClient.get(`/api/orden/cliente/${id}`);
};

const create = (orden) => {
    return httpClient.post('/api/orden/crear', orden);
  };

const update = (id, orden) => {
  return httpClient.put(`/api/orden/${id}`, orden);
};
  
const remove = (id) => {
  return httpClient.delete(`/api/orden/${id}`);
};

const getOrdersWithinRadius = (idTienda, radiusKm) => {
  return httpClient.get('/api/orden/inradius', {
    params: {
      idTienda: idTienda,
      radiusKm: radiusKm
    }
  });
};

export default { getAll, getById, getByClientId, create, update, remove, getOrdersWithinRadius };