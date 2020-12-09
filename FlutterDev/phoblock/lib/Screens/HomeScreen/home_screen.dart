import 'package:flutter/material.dart';
import '../../style.dart';

// ignore: must_be_immutable
class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Home'),
        backgroundColor: hexToColor('#64B6A9'),
      ),
    );
  }
}
