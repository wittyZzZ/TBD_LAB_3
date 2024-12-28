import httpClient from "../http-common"

const getAll = () => {
  return httpClient.get('/api/producto/');
};

const getById = id => {
  return httpClient.get(`/api/producto/${id}`);
};

const update = (id, producto) => {
  return httpClient.put(`/api/producto/${id}`, producto);
};

const remove = (id) => {
  return httpClient.delete(`/api/producto/${id}`);
};

export default { getAll, getById, update, remove };




