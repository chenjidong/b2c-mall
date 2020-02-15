import request from '@/utils/request'

export function listAdmin(query) {
  return request({
    url: '/admin/list',
    method: 'get',
    params: {
      ...query
    }
  })
}

export function createAdmin(data) {
  return request({
    url: '/admin/create',
    method: 'post',
    data: {
      ...data
    }
  })
}

export function readminAdmin(data) {
  return request({
    url: '/admin/readmin',
    method: 'get',
    data: {
      ...data
    }
  })
}

export function updateAdmin(data) {
  return request({
    url: '/admin/create',
    method: 'post',
    data: {
      ...data
    }
  })
}

export function deleteAdmin(id) {
  return request({
    url: '/admin/delete',
    method: 'post',
    params: {
      id: id
    }
  })
}
