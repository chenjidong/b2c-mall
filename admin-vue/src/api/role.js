import request from '@/utils/request'

export function listRole(query) {
  return request({
    url: '/role/list',
    method: 'get',
    params: {
      ...query
    }
  })
}

export function createRole(data) {
  return request({
    url: '/role/create',
    method: 'post',
    data: {
      ...data
    }
  })
}

export function updateRole(data) {
  return request({
    url: '/role/create',
    method: 'post',
    data: {
      ...data
    }
  })
}

export function deleteRole(id) {
  return request({
    url: '/role/delete',
    method: 'post',
    params: {
      id: id
    }
  })
}

export function getPermission(query) {
  return request({
    url: '/permission/list',
    method: 'get',
    params: {
      ...query
    }
  })
}

export function updatePermission(data) {
  return request({
    url: '/permission/update',
    method: 'post',
    data: {
      ...data
    }
  })
}

export function roleOptions(query) {
  return request({
    url: '/role/options',
    method: 'get'
  })
}
