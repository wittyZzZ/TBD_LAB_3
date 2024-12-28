import httpClient from "../http-common";

const getAll = () => {
  return httpClient.get("/api/repartidor/");
};

const getById = (id) => {
  return httpClient.get(`/api/repartidor/${id}`);
};

const create = (repartidor) => {
  return httpClient.post("/api/repartidor/", repartidor);
};

const update = (repartidor) => {
  return httpClient.put("/api/repartidor/", repartidor);
};

const remove = (id) => {
  return httpClient.delete(`/api/repartidor/${id}`);
};

const getInRadius = (id_tienda_input, radius_km) => {
  return httpClient.get("/api/repartidor/inradius", {
    params: { id_tienda_input, radius_km }
  });
};

export default { getAll, getById, create, update, remove , getInRadius};