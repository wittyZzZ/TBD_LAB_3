import httpClient from "../http-common"

const getAll = () => {
  return httpClient.get('/api/tienda/');
};

export default { getAll };