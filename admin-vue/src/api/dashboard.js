import request from '@/utils/request'

export function info() {
  return request({
    url: '/admin/dashboard',
    method: 'get'
  })
}
