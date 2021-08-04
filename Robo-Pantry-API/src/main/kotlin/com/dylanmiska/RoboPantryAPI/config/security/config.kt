package com.dylanmiska.RoboPantryAPI.config.security


//
//@Configuration
//class config(): WebSecurityConfigurerAdapter() {
//
//    override fun configure(http: HttpSecurity) {
//        http.csrf().disable()
//            .authorizeRequests()
//            .antMatchers("/**").permitAll()
//            .anyRequest().authenticated()
//            .and().sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
//    }
//
//}