#pragma once
#include "stdafx.h"
#include "VisibleGameObject.h"
#include "ServiceLocator.h"

class TimeBar
{
public:
	TimeBar();
	bool isOver(sf::RenderWindow&, float);
	static void setTotalTime(float);
	void reset();

private:
	float _remainingTime;
	static float _totalTime;
};

