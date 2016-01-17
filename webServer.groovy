#!/usr/bin/env groovy

@Grab(group='org.eclipse.jetty', module='jetty-server', version='9.3.7.v20160115')
@Grab(group='org.eclipse.jetty', module='jetty-servlet', version='9.3.7.v20160115')

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.*
import groovy.servlet.*

def startJetty() {
    def server = new Server(8080)

    def handler = new ServletContextHandler(ServletContextHandler.SESSIONS)
    handler.contextPath = '/'
    handler.resourceBase = '.'
    handler.addServlet(GroovyServlet, '/scripts/*')
    //def filesHolder = handler.addServlet(DefaultServlet, '/')
    //filesHolder.setInitParameter('resourceBase', './public')

    server.handler = handler
    server.start()
}

println "Starting Jetty, press Ctrl+C to stop."
startJetty()
