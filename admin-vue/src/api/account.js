import request from '@/utils/request'

export function fetchList(query) {
  return request({
    method: 'get',
    url: '/account/list',
    params: {
      ...query
    }
  })
}

export function activeUser(data) {
  return request({
    method: 'post',
    url: '/account/updateStatus',
    params: {
      id: data.id,
      status: data.status
    }
  })
}

export function createUser(data) {
  return request({
    method: 'post',
    url: '/account/createByAdmin',
    data: {
      ...data
    }
  })
}

export function updateUser(data) {
  return request({
    method: 'post',
    url: '/account/createByAdmin',
    data: {
      ...data
    }
  })
}

export function deleteUser(id, nickname) {
  return request({
    method: 'post',
    url: '/account/delete',

    params: {
      id: id
    }
  })
}
