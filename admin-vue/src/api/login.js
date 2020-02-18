import request from '@/utils/request'

export function loginByUsername(username, password, verifyCode) {
  const data = {
    username,
    password,
    verifyCode
  }
  return request({
    url: '/login/loginByUsername',
    method: 'post',
    params: {
      ...data
    }
  })
}

export function sendMsg(data) {
  return request({
    url: '/login/sendLoginMsg',
    method: 'post',
    data: {
      ...data
    }
  })
}

export function logout() {
  return request({
    url: '/admin/logout',
    method: 'post'
  })
}

export function getUserInfo(token) {
  return request({
    url: '/admin/getInfo',
    method: 'post'
  })
}

