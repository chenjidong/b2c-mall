import request from '@/utils/request'

export function changePassword(data) {
  return request({
    url: '/admin/changePassword',
    method: 'post',
    params: {
      ...data
    }
  })
}
