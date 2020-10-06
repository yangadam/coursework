#pragma once
#include "stdafx.hpp"
#include "VisibleGameObject.hpp"
#include "ServiceLocator.hpp"

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

