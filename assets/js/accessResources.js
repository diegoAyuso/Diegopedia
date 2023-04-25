const service = {
  url_base: 'http://localhost:9097/diegopedia',
  addCientifico: async function (data) {
    return fetch(this.url_base + '/cientificos', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json; charset=utf-8'
      },
      body: JSON.stringify(data)
    })
      .then(response => response.json())
      .then(data => data)
  },
  updateCientifico: async function (id, data) {
    return fetch(this.url_base + '/cientificos/' + id, {
      method: 'PUT',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json; charset=utf-8'
      },
      body: JSON.stringify(data)
    })
      .then(response => response.json())
      .then(data => data)
  },
  removeCientifico: async function (id) {
    return fetch(this.url_base + '/cientificos/' + id, {
      method: 'DELETE'
    })
      .then(response => response.json())
      .then(data => data)
  },
  getCientificosAll: async function () {
    return await fetch(this.url_base + '/cientificos')
      .then(response => response.json())
      .then(data => data)
  },
  getCientificoById: async function (id) {
    return fetch(this.url_base + '/cientificos/' + id)
      .then(response => response.json())
      .then(data => data)
  },
  getCientificosByRamaContribucion: async function (rama) {
    return fetch(this.url_base + '/cientificos/busquedaAjena/rama/' + rama)
      .then(response => response.json())
      .then(data => data)
  },
  getAllCientificosByNombre: async function (nombreCientifico) {
    return fetch(this.url_base + '/cientificos/busquedaPropia/nombre/' + nombreCientifico)
      .then(response => response.json())
      .then(data => data)
  },
  getAllCientificosByApellidos: async function (apellidosCientifico) {
    return fetch(this.url_base + '/cientificos/busquedaPropia/apellidos/' + apellidosCientifico)
      .then(response => response.json())
      .then(data => data)
  },
  getAllCientificosByNacionalidad: async function (nacionalidadCientifico) {
    return fetch(this.url_base + '/cientificos/busquedaPropia/nacionalidad/' + nacionalidadCientifico)
      .then(response => response.json())
      .then(data => data)
  },
  getAllCientificosByNacimiento: async function (nacimientoCientifico) {
    return fetch(this.url_base + '/cientificos/busquedaPropia/fechaNacimiento/' + nacimientoCientifico)
      .then(response => response.json())
      .then(data => data)
  },
  getAllCientificosByDefuncion: async function (defuncionCientifico) {
    return fetch(this.url_base + '/cientificos/busquedaPropia/fechaDefuncion/' + defuncionCientifico)
      .then(response => response.json())
      .then(data => data)
  },
  getContribucionesAll: async function () {
    return await fetch(this.url_base + '/contribuciones')
      .then(response => response.json())
      .then(data => data)
  },
  getContribucionById: async function (id) {
    return fetch(this.url_base + '/contribuciones/' + id)
      .then(response => response.json())
      .then(data => data)
  },
  getAllContribucionesByNombre: async function (nombreContribucion) {
    return fetch(this.url_base + '/contribuciones/busquedaPropia/nombre/' + nombreContribucion)
      .then(response => response.json())
      .then(data => data)
  },
  getAllContribucionesByCampo: async function (campoContribucion) {
    return fetch(this.url_base + '/contribuciones/busquedaPropia/campo/' + campoContribucion)
      .then(response => response.json())
      .then(data => data)
  },
  getAllContribucionesByRama: async function (ramaContribucion) {
    return fetch(this.url_base + '/contribuciones/busquedaPropia/rama/' + ramaContribucion)
      .then(response => response.json())
      .then(data => data)
  },
  getRelacionesCientificoContribucionAll: async function () {
    return await fetch(this.url_base + '/relacionesCientificoContribucion')
      .then(response => response.json())
      .then(data => data)
  },
  getRelacionCientificoContribucionById: async function (id) {
    return fetch(this.url_base + '/relacionesCientificoContribucion/' + id)
      .then(response => response.json())
      .then(data => data)
  },
  getAllRelacionesCientificoContribucionByNombreContribucion: async function (nombreContribucion) {
    return fetch(this.url_base + '/relacionesCientificoContribucion/busquedaPropia/nombreContribucion/' + nombreContribucion)
      .then(response => response.json())
      .then(data => data)
  },
  getAllRelacionesCientificoContribucionByApellidosCientifico: async function (apellidosCientifico) {
    return fetch(this.url_base + '/relacionesCientificoContribucion/busquedaPropia/apellidosCientifico/' + apellidosCientifico)
      .then(response => response.json())
      .then(data => data)
  },
  getAllRelacionesCientificoContribucionByEpoca: async function (epoca) {
    return fetch(this.url_base + '/relacionesCientificoContribucion/busquedaPropia/epoca/' + epoca)
      .then(response => response.json())
      .then(data => data)
  },
  getAllRelacionesCientificoContribucionByAportacion: async function (aportacion) {
    return fetch(this.url_base + '/relacionesCientificoContribucion/busquedaPropia/aportacion/' + aportacion)
      .then(response => response.json())
      .then(data => data)
  },
  getUsuariosAll: async function () {
    return await fetch(this.url_base + '/usuarios')
      .then(response => response.json())
      .then(data => data)
  },
  getUsuarioById: async function (id) {
    return fetch(this.url_base + '/usuarios/' + id)
      .then(response => response.json())
      .then(data => data)
  },
  updateUsuario: async function (id, data) {
    return fetch(this.url_base + '/usuarios/' + id, {
      method: 'PUT',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json; charset=utf-8'
      },
      body: JSON.stringify(data)
    })
      .then(response => response.json())
      .then(data => data)
  },
  getAllUsuariosByNombre: async function (nombreUsuario) {
    return fetch(this.url_base + '/usuarios/busquedaPropia/nombre/' + nombreUsuario)
      .then(response => response.json())
      .then(data => data)
  },
  getAllUsuariosByPassword: async function (passwordUsuario) {
    return fetch(this.url_base + '/usuarios/busquedaPropia/password/' + passwordUsuario)
      .then(response => response.json())
      .then(data => data)
  },
  getAllUsuariosByNombreFull: async function (nombreUsuario) {
    return fetch(this.url_base + '/usuarios/busquedaPropia/nombreFull/' + nombreUsuario)
      .then(response => response.json())
      .then(data => data)
  },
  getAllUsuariosByPasswordFull: async function (passwordUsuario) {
    return fetch(this.url_base + '/usuarios/busquedaPropia/passwordFull/' + passwordUsuario)
      .then(response => response.json())
      .then(data => data)
  },
  getRelacionesUsuarioCientificoAll: async function () {
    return await fetch(this.url_base + '/relacionesUsuarioCientifico')
      .then(response => response.json())
      .then(data => data)
  },
  getRelacionUsuarioCientificoById: async function (id) {
    return fetch(this.url_base + '/relacionesUsuarioCientifico/' + id)
      .then(response => response.json())
      .then(data => data)
  },
  getAllRelacionesUsuarioCientificoByNombreUsuario: async function (nombreUsuario) {
    return fetch(this.url_base + '/relacionesUsuarioCientifico/busquedaPropia/usuario/' + nombreUsuario)
      .then(response => response.json())
      .then(data => data)
  },
  getAllRelacionesUsuarioCientificoByApellidosCientifico: async function (apellidosCientifico) {
    return fetch(this.url_base + '/relacionesUsuarioCientifico/busquedaPropia/cientifico/' + apellidosCientifico)
      .then(response => response.json())
      .then(data => data)
  },
  getRelacionesUsuarioContribucionAll: async function () {
    return await fetch(this.url_base + '/relacionesUsuarioContribucion')
      .then(response => response.json())
      .then(data => data)
  },
  getRelacionUsuarioContribucionById: async function (id) {
    return fetch(this.url_base + '/relacionesUsuarioContribucion/' + id)
      .then(response => response.json())
      .then(data => data)
  },
  getAllRelacionesUsuarioContribucionByNombreUsuario: async function (nombreUsuario) {
    return fetch(this.url_base + '/relacionesUsuarioContribucion/busquedaPropia/usuario/' + nombreUsuario)
      .then(response => response.json())
      .then(data => data)
  },
  getAllRelacionesUsuarioContribucionByNombreContribucion: async function (nombreContribucion) {
    return fetch(this.url_base + '/relacionesUsuarioContribucion/busquedaPropia/contribucion/' + nombreContribucion)
      .then(response => response.json())
      .then(data => console.log(data))
  },
  addUsuario: async function (data) {
    return fetch(this.url_base + '/usuarios', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json; charset=utf-8'
      },
      body: JSON.stringify(data)
    })
      .then(response => response.json())
      .then(data => data)
  },
  removeUsuario: async function (id) {
    return fetch(this.url_base + '/usuarios/' + id, {
      method: 'DELETE'
    })
      .then(response => response.json())
      .then(data => data)
  },
  addUsuarioCientifico: async function (data) {
    return fetch(this.url_base + '/relacionesUsuarioCientifico', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json; charset=utf-8'
      },
      body: JSON.stringify(data)
    })
      .then(response => response.json())
      .then(data => data)
  },
  addUsuarioContribucion: async function (data) {
    return fetch(this.url_base + '/relacionesUsuarioContribucion', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json; charset=utf-8'
      },
      body: JSON.stringify(data)
    })
      .then(response => response.json())
      .then(data => data)
  },
  deleteUsuarioCientifico: async function (id) {
    return fetch(this.url_base + '/relacionesUsuarioCientifico/' + id, {
      method: 'DELETE'
    })
      .then(response => response.json())
      .then(data => data)
  },
  deleteUsuarioContribucion: async function (id) {
    return fetch(this.url_base + '/relacionesUsuarioContribucion/' + id, {
      method: 'DELETE'
    })
      .then(response => response.json())
      .then(data => data)
  }
}