# easyRPC
 A simple RPC framework-一个简单的 RPC 框架


> todo list
> - 本地服务保存到 redis
> - 增加可用服务的负载均衡方式，一致性hash、轮询、随机等
> - 目前所用协议为 tcp，后续考虑自定义协议（自定义请求头，请求体，请求提长度等）

## 学习路线
### 从服务提供者 -> 服务消费者路线
#### provider端：
注册本地服务【LocalRegistry】</br>
↓</br>
注册实例为可用实例【NacosRegistry】</br>
↓</br>
启动服务并阻塞监听请求【HttpService】</br>

#### consumer端
获取服务提供者的代理对象【ProxyFactory】</br>
↓</br>
发起调用【HttpClint】</br>

### RPC整体解析
监听 http 端口</br>
↓</br>
接收请求</br>
↓</br>
解析请求</br>
↓</br>
获取服务提供者</br>
↓</br>
发起调用</br>
↓</br>
返回结果</br>



