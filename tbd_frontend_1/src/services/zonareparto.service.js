import httpClient from "../http-common"

const getAll = () => {
  return httpClient.get('/api/zona-reparto/');
};

const getById = id => {
  return httpClient.get(`/api/zona-reparto/${id}`);
};

const create = (zona) => {
    return httpClient.post('/api/zona-reparto/', zona);
  };

const update = (id, zona) => {
  return httpClient.put(`/api/zona-reparto/${id}`, zona);
};
  
const remove = (id) => {
  return httpClient.delete(`/api/zona-reparto/${id}`);
};


const getDeliveriesInPolygon = (id_poligono) => {
  return httpClient.get('/api/zona-reparto/deliveryinpolygon', {
    params: {
      id_poligono: id_poligono
    }
  });
};

export default { getAll, getById, create, update, remove, getDeliveriesInPolygon };