import http from 'k6/http';

export default function () {
//  const url = 'http://localhost:8080/mock';
  const url = 'http://192.168.1.69:8080/mock';
  const payload = JSON.stringify({
    name: 'ОАО Ромашка',
    inn: '7707083894',
    ogrn: '1027700132195',
    kpp: '973643001',
    appId: '4097643769930881329',
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  http.post(url, payload, params);
}
