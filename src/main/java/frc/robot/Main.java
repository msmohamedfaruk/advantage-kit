// Copyright 2021-2024 FRC 6328
// http://github.com/Mechanical-Advantage
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// version 3 as published by the Free Software Foundation or
// available in the root directory of this project.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.

package frc.robot;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.javadsl.Behaviors;
import frc.robot.actor.HelloWorldMain;
import frc.robot.actor.HelloWorldMain.SayHello;

/**
 * Do NOT add any static variables to this class, or any initialization at all. Unless you know what
 * you are doing, do not modify this file except to change the parameter class to the startRobot
 * call.
 */
public final class Main {
  private Main() {}

  /**
   * Main initialization function. Do not perform any initialization here.
   *
   * <p>If you change your main robot class, change the parameter type.
   */
  public static void main(String... args) {
    // unitTestHello();
    unitTest();
  }

  public static void unitTestHello() {
    final ActorSystem<SayHello> system = ActorSystem.create(HelloWorldMain.create(), "hello");

    system.tell(new HelloWorldMain.SayHello("World"));
    system.tell(new HelloWorldMain.SayHello("Akka"));
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    system.terminate();
  }

  public static void unitTest() {
    var a = ActorSystem.<String>create(Behaviors.logMessages(Behaviors.ignore()), "Machine");
    // the code fails on the above line, before anything else can happen

    System.out.print("\n".repeat(5));

    a.tell("hello");

    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.print("\n".repeat(5));

    a.terminate();
  }
}
