# Story（Calarification Needed）
**作为** 普通停车用户
**我想要** 自助停车和取车
**从而** 节省我停车和取车的时间

---

# Tasking（Business-Oriented Tasking）
## 停车成功
### AC1：有车位停车取票
- Example
    -  Given 停车场容量10，已停0辆车  When 停车, Then 停车成功，返回票据
    -  Given 停车场容量10，已停8辆车  When 停车, Then 停车成功，返回票据
## 停车失败
### AC2：无空位返回提示”车位已满“
- Example
    -  Given 停车场容量10，已停10辆车 When 停车, Then 停车失败,提示“车位已满”

## 取车成功
### AC3：用户凭票据取车成功
- Example
    -  Given 用户在停车场停车，并获得票据, When取A车, Then 成功取到A车，提示“取车成功”

##取车失败
### AC4：使用其他停车场票据取车失败，提示“无效票”
- Example
    -  Given 用户使用其他停车场票据, When取车, Then 取车失败，提示“无效票”

### AC5：使用已取车的票据取车失败，提示“无效票”
- Example
    -  Given 用户使用已取车的票据, When取车, Then 取车失败，提示“无效票”

