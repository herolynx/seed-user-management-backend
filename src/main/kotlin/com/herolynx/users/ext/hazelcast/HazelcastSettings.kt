package com.herolynx.users.ext.hazelcast

import com.hazelcast.client.config.ClientConfig
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "hazelcast")
class HazelcastSettings {

    var ip: String? = null
    var port: Int? = null
    var attemptLimit: Int = 5
    var attemptPeriod: Int = 500
    var connTimeout: Int = 500
    var user: String? = null
    var pass: String? = null

    fun hazelcastConfig(): ClientConfig {
        val clientConfig = ClientConfig()
        clientConfig.networkConfig
                .addAddress("$ip:$port")
                .setConnectionAttemptLimit(attemptLimit)
                .setConnectionAttemptPeriod(attemptPeriod)
                .setConnectionTimeout(connTimeout)
        clientConfig.groupConfig.name = user
        clientConfig.groupConfig.password = pass
        return clientConfig
    }

}
