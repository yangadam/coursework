#pragma once
#include "stdafx.h"
#include "Position.h"
#include "Brick.h"
#include "ServiceLocator.h"


class BrickManager
{
public:
	BrickManager();
	~BrickManager();
	void initialize();
	void updateAll(float);
	void drallAll(sf::RenderWindow&);
	VisibleGameObject* operator[](int);
	VisibleGameObject* operator()(sf::Vector2i);
	int handleClick(Position&);

	static void setRate(int);
	static int getRate();

private:
	static int _rate;
	const int vertical, parallel, width, height;
	VisibleGameObject* _pBricks[25*17];
	
};

