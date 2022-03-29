import axios from "axios"

axios.defaults.baseURL = 'http://localhost:8080/';

axios.interceptors.response.use(function (response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    console.log('succeed')
    return response;
  }, function (error) {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // Do something with response error
    console.log('exception');
    return error;
  });
  
  export default axios;