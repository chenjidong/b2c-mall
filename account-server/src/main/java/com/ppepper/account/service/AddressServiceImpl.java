package com.ppepper.account.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ppepper.account.domain.AccountDO;
import com.ppepper.account.domain.AddressDO;
import com.ppepper.account.mapper.AccountMapper;
import com.ppepper.account.mapper.AddressMapper;
import com.ppepper.common.dto.AddressDTO;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.service.BaseServiceImpl;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-11
 */
@Service
public class AddressServiceImpl extends BaseServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public AjaxResult get(Long accountId, Long id) {
        AddressDO addressDO = new AddressDO();
        addressDO.setId(id);
        addressDO.setAccountId(accountId);
        addressDO = addressMapper.selectOne(addressDO);
        if (addressDO == null)
            return error("地址不存在！");
        return toAjax(copyProperties(addressDO, AddressDTO.class));
    }

    @Override
    public AjaxResult list(Long accountId, Integer pageNo, Integer pageSize, String phone) {
        Wrapper<AddressDO> wrapper = new EntityWrapper<>();
        wrapper.eq("account_id", accountId);
        if (phone != null)
            wrapper.like("phone", phone);
        List<AddressDO> addressDOList = addressMapper.selectPage(new RowBounds((pageNo - 1) * pageSize, pageSize), wrapper);
        if (addressDOList != null) {
            List<AddressDTO> addressDTOS = copyListProperties(addressDOList, AddressDTO.class);
            return toAjax(addressDTOS);
        }
        return error("未查询到数据");
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public AjaxResult add(Long accountId, String province, String city, String county, String address, String phone, String consignee, Boolean isDefault) {
        AccountDO accountDO = accountMapper.selectById(accountId);
        if (accountDO == null)
            return error("账户不存在");

        if (isDefault) {
            List<AddressDO> oldList = addressMapper.selectList(new EntityWrapper<AddressDO>().eq("account_id", accountId));
            if (oldList != null && !oldList.isEmpty()) {
                oldList.forEach(item -> {
                    if (item.getDefaultAddress() != null && item.getDefaultAddress()) {
                        AddressDO addressDO = new AddressDO();
                        addressDO.setId(item.getId());
                        addressDO.setDefaultAddress(false);
                        addressMapper.updateById(addressDO);
                    }
                });
            }
        }
        AddressDO addressDO = new AddressDO();
        addressDO.setProvince(province);
        addressDO.setCounty(county);
        addressDO.setAddress(address);
        addressDO.setPhone(phone);
        addressDO.setConsignee(consignee);
        addressDO.setDefaultAddress(isDefault);

        int count = addressMapper.insert(addressDO);

        return toAjax(count);
    }

    @Override
    public AjaxResult del(Long accountId, Long id) {
        AddressDO addressDO = new AddressDO();
        addressDO.setId(id);
        addressDO.setAccountId(accountId);
        addressDO = addressMapper.selectOne(addressDO);
        if (addressDO == null)
            return error("地址不存在或已删除");
        int count = addressMapper.deleteById(id);
        return toAjax(count);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public AjaxResult setDefault(Long accountId, Long id, Boolean isDefault) {
        AddressDO old = new AddressDO();
        old.setId(id);
        old.setAccountId(accountId);
        old = addressMapper.selectOne(old);
        if (old == null)
            return error("地址不存在或已删除");
        if (isDefault) {
            List<AddressDO> oldList = addressMapper.selectList(new EntityWrapper<AddressDO>().eq("account_id", accountId));
            if (oldList != null && !oldList.isEmpty()) {
                oldList.forEach(item -> {
                    if (item.getDefaultAddress() != null && item.getDefaultAddress()) {
                        AddressDO addressDO = new AddressDO();
                        addressDO.setId(item.getId());
                        addressDO.setDefaultAddress(false);
                        addressMapper.updateById(addressDO);
                    }
                });
            }
        }
        old.setDefaultAddress(isDefault);
        int count = addressMapper.updateById(old);
        return toAjax(count);
    }
}
