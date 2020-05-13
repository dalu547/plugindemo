/********* PluginDemo.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>

@interface PluginDemo : CDVPlugin {
  // Member variables go here.
}
@property ( nonatomic, retain ) NSString * eventTriggerObj;
- (void)coolMethod:(CDVInvokedUrlCommand*)command;
- (void)init:(CDVInvokedUrlCommand*)command;
- (void)unsubscribe:(CDVInvokedUrlCommand*)command;
- (void)subscribe:(CDVInvokedUrlCommand*)command;
- (void)unregister:(CDVInvokedUrlCommand*)command;
- (void)initRegistration;
@end

@implementation PluginDemo

@synthesize eventTriggerObj;

- (void)coolMethod:(CDVInvokedUrlCommand*)command {
    CDVPluginResult* pluginResult = nil;
    NSString* echo = [command.arguments objectAtIndex:0];
    
    //self.eventTriggerObj = command.callbackId;
    NSLog(@"callbackID:%@", self.eventTriggerObj);

    if (echo != nil && [echo length] > 0) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:echo];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }
    [NSTimer scheduledTimerWithTimeInterval:5 target:self selector:@selector(timerCalled) userInfo:nil repeats:YES];

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)timerCalled {
    NSLog(@"Timer Called");
    NSLog(@"Timer callbackID:%@", self.eventTriggerObj);
    [self sendMessage:@"initRegistration"];
}

-(void)initRegistration {
    [self sendMessage:@"initRegistration"];
}

- (void)init:(CDVInvokedUrlCommand*)command {
    self.eventTriggerObj = command.callbackId;
    [self sendMessage:@"init"];
}

- (void)unsubscribe:(CDVInvokedUrlCommand*)command {
    [self sendMessage:@"unsubscribe"];
}

- (void)subscribe:(CDVInvokedUrlCommand*)command{
    [self sendMessage:@"subscribe"];
}

- (void)unregister:(CDVInvokedUrlCommand*)command {
    [self sendMessage:@"unregister"];
}

- (void)sendMessage:(NSString*)message {
    NSLog(@"Send Message:%@", message);
    CDVPluginResult *pluginResult = [ CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:message];
    [pluginResult setKeepCallbackAsBool:true ];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:self.eventTriggerObj];
}

@end
