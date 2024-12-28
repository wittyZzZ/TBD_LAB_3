import httpClient from "../http-common"

const getAll = () => {
    return httpClient.get(`/api/categoria/`);
  };
const getById = id => {
  return httpClient.get(`/api/categoria/${id}`);
};


export default { getAll, getById };