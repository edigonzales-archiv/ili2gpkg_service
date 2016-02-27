#!/usr/bin/env groovy

@Grab(group='org.eclipse.jetty', module='jetty-server', version='8.1.7.v20120910')
@Grab(group='org.eclipse.jetty', module='jetty-servlet', version='8.1.7.v20120910')

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.*
import groovy.servlet.*

def startJetty() {
    def server = new Server(8080)

    def handler = new ServletContextHandler(ServletContextHandler.SESSIONS)
    handler.contextPath = '/'
    handler.resourceBase = '.'
    handler.addServlet(GroovyServlet, '/scripts/*')    

    def filesHolder = handler.addServlet(DefaultServlet, '/')
    filesHolder.setInitParameter('resourceBase', './public')

    server.handler = handler
    server.start()
}

println "Starting Jetty, press Ctrl+C to stop."
startJetty()
