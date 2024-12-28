import httpClient from "../http-common"

const getAll = () => {
  return httpClient.get('/api/detalle_orden/');
};


const create = (detalleOrden) => {
    return httpClient.post(`/api/detalle_orden/`, detalleOrden);
};


export default { getAll, create };