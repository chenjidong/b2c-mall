import request from '@/utils/request'

export function loginByUsername(phone, pwd, verifyCode) {
  const data = {
    phone,
    pwd,
    verifyCode
  }
  return request({
    url: process.env.HOST + '/api/sso/login',
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

